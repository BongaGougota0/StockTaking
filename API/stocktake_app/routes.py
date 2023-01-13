# -*- coding: utf-8 -*-
"""
Created on Fri Jan 13 02:54:21 2023

@author: gmvn
"""
from flask import Flask, render_template, url_for, flash, json, redirect
from stocktake import app
from stocktake.forms import Login, Registration
from stocktake.models import User, List

'''--------------------------------Site Admin API End Points'''

@app.route("/")
@app.route("/login", methods=["GET","POST"])
def login():
    form = Login()
    return render_template('login.html', title='login', form=form)
	
@app.route("/register", methods=["GET", "POST"])
def register_new_user():
    form = Registration()
    return render_template('register.html', title='Register', form=form)

@app.route("/dashboard", methods=["GET","POST"])
def dashboard_view():
    data_my = [11, 92, 45, 32, 34, 52, 41]
    return render_template('dashboard.html', title='Dashboard', my_data = json.dumps(data_my))


'''----------------------------------App API End Points'''
@app.route("/createlist", methods=["Post"])
def savelist():
    return "<p>Hello, World!</p>"