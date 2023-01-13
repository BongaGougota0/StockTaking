# -*- coding: utf-8 -*-
"""
Created on Fri Jan 13 02:54:01 2023

@author: gmvn
"""
from datetime import datetime
from stocktake_app import db

class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(20), unique=True, nullable=False)
    email = db.Column(db.String(30), unique=True, nullable=False)
    password = db.Column(db.String(20), unique=True, nullable=False)

    #relationship between user and the lists they create
    lists = db.relationship('List', backref='creator', lazy=True)

    #hashing algorithm used 60 chars
    image_file = db.Column(db.String(60), nullable=False, default='default.jpg')

    def __repr__(self):
        return f"User('{self.username}', '{self.email}', '{self.image_file}')"


class List(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    items = db.Column(db.String(600), nullable=False) #should be an array of items
    date_created = db.Column(db.String(30), nullable=False, default=datetime.utcnow)
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)

    def __repr__(self):
        return f"List('{self.items}', '{self.date_created}')"