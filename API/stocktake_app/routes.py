# -*- coding: utf-8 -*-
"""
Created on Fri Jan 13 02:54:21 2023

@author: gmvn
"""
from flask import Flask, render_template, url_for, flash, json, redirect, jsonify, request
from stocktake_app import app, bcrypt, db
from flask_login import current_user, logout_user
from stocktake_app.forms import Login, Registration, NewProduct
from stocktake_app.models import User, List, Admin, Category, Product, Store, UserAppModel



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






'''----------------------------------Mobile Application API End Points --- Begin'''

@app.route("/app/createlist", methods=["Post"])
def appSavelist():
    data = request.get_json()

    items = data['items']
    date_created = data['data_created']
    items_count = data['items_count']
    total_price = data['total_price']
    user_id = data['user_id']

    list = List(items=items.data, date_created=date_created.data,
    items_count=items_count.data, total_price=total_price.data)

    db.session.add(list)
    db.session.commit()

    return jsonify({'Message':'Your list was created and saved.', 'Status':'Success'})

@app.route("/app/login", methods=["Post", "GET"])
def appLogin():
    data = request.get_json()

    username = data['username']
    password = data['password']
    '''Verify login details - Correctness.'''
    user = User.query.filter_by(username= username.data).first()
    if user and bcrypt.check_password_hash(user.password, password.data):
        return jsonify({'Message':'Login Success', 'Status':'Success'})
    else:
        return jsonify({'Message':'Please check your details.', 'Status':'Unsuccessful'})

@app.route("/app/register", methods=["Post", "GET"])
def appRegister():
    data = request.get_json()

    name = data['name']
    email = data['email']
    username = data['username']
    location = data['location']
    password = data['password']
    hashpassword = bcrypt.generate_password_hash(password=password.data).decode('utf-8')

    user = UserAppModel.query.filter_by(username=username.data).first()
    if user:
        return jsonify({'Message':"Username already takekn.",'Status':'Unsuccesful'})
    else:
        em = UserAppModel.query.filter_by(email=email.data).first()
        if em:
            mssg = validate_email(em.email)
            return jsonify({'Message':mssg,'Status':'Unsuccesful'})
        else:
            user = UserAppModel(name=name.data, email=email.data, username=username.data,
             location=location.data, password=hashpassword)

        db.session.add(user)
        db.session.commit()
        return jsonify({'Message':'User account created.', 'Status':'Success'})

def validate_username(username):
            user = User.query.filter_by(username=username.data).first()
            if user:
                return False
            else:
                return True

def validate_email(email):
            user = User.query.filter_by(email=email.data).first()
            if user:
                return False
            else:
                return True

@app.route("/app/update", methods=["Post", "GET"])
def appUpdate():
    data = request.get_json()

    name = data['name']
    email = data['email']
    username = data['username']
    location = data['location']
    password = data['password']

    user = UserAppModel.query.filter_by(username=username.data)

    if user.username != username.data and validate_username(username.data):
        #username available, continue ...
        if user.email != email.data and validate_email(email.data):
            #continue email not used before...
            updateuser = UserAppModel(name=name.data, email=email.data,username=username.data,
            location=location.data,password=password.data)
            db.session.add(updateuser)
            db.session.commit()
        else:
            return jsonify({'Message':'Email already exists', 'Status':'Unsuccesful'})
    else:
        return jsonify({'Message':'Username already exists', 'Status':'Unsuccesful'})

    

'''----------------------------------Mobile Application API End Points --- End'''