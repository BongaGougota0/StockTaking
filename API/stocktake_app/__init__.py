# -*- coding: utf-8 -*-
"""
Created on Fri Jan 13 02:54:41 2023

@author: gmvn
"""
from flask import Flask, render_template, url_for, flash, json, redirect
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

app.config['SECRET_KEY'] = '51dae20a00f6f3edee3a23f7fe4907c2'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///mysite.db'

#database instance
db = SQLAlchemy(app)

with app.app_context():
    db.create_all()
    
from stocktake_app import routes