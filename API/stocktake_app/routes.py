# -*- coding: utf-8 -*-
"""
Created on Fri Jan 13 02:54:21 2023

@author: gmvn
"""
from flask import Flask, render_template, url_for, flash, json, redirect
from stocktake_app import app, bcrypt, db
from flask_login import current_user, logout_user
from stocktake_app.forms import Login, Registration, NewProduct
from stocktake_app.models import User, List, Admin, Category, Product, Store

'''--------------------------------Site Admin API End Points'''

@app.route("/login", methods=["GET","POST"])
def login():
    if current_user.is_authenticated:
        return redirect( url_for(dashboard_view))
    form = Login()
    if form.validate_on_submit:
        '''Verify login details - Correctness.'''
        user = User.query.filter_by(username= form.username.data).first()
        if user and bcrypt.check_password_hash(user.password, form.password.data):
            flash(f"Login Succesfull!", 'success')
            return redirect( url_for(dashboard_view))
        else:
            flash(f"Login Unsuccesfull, please check you details", 'danger')
    return render_template('login.html', title='login', form=form)

	
@app.route("/register", methods=["GET", "POST"])
def register_new_user():
    if current_user.is_authenticated:
        return redirect( url_for(dashboard_view))
    form = Registration()
    if form.validate_on_submit:
        hash_password = bcrypt.generate_password_hash(form.password.data).decode('utf-8')
        user = Admin(username=form.username, email=form.email, password=hash_password)
        db.session.add(user)
        db.session.commit()
        flash(f"Account created. Now Login")
        return redirect( url_for(login))
    return render_template('register.html', title='Register', form=form)

@app.route("/dashboard", methods=["GET","POST"])
def dashboard_view():
    data_my = [40, 92, 45, 32, 34, 52, 41]
    return render_template('dashboard.html', 
    title='Dashboard', my_data = json.dumps(data_my))

@app.route("/new_product", methods=["GET","POST"])
def add_product():
    form = NewProduct()
    return render_template("new_product.html", form=form)

'''----------------------------------App API End Points'''
@app.route("/createlist", methods=["Post"])
def savelist():
    return "<p>Hello, World!</p>"