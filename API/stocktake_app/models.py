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
    password = db.Column(db.String(20), nullable=False)

    #relationship between user and the lists they create
    lists = db.relationship('List', backref='creator', lazy=True)

    #hashing algorithm used 60 chars
    image_file = db.Column(db.String(60), nullable=False, default='default.jpg')

    def __repr__(self):
        return f"User('{self.username}', '{self.email}', '{self.image_file}')"
        
class List(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    items = db.Column(db.String(600), nullable=False) #should be an array of items
    date_created = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)
    items_count = db.Column(db.String(5), nullable=False)
    total_price = db.Column(db.String(5), nullable=False)

    #references {{creator}} property
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)

    def __repr__(self):
        return f"List('{self.items}', '{self.date_created}')"

class Admin(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(20), unique=True, nullable=False)
    email = db.Column(db.String(30), unique=True, nullable=False)
    password = db.Column(db.String(20), unique=True, nullable=False)

    #relationship between user and the lists they create
    stores = db.relationship('Store', backref='store_admin', lazy=True)

    #Use dropdown menu on the front - end instead of complex relationships
    #Categories created
    #cate = db.relationship('Category', backref='store_manager', lazy=True)

    #hashing algorithm used 60 chars
    image_file = db.Column(db.String(60), nullable=False, default='default.jpg')

    def __repr__(self):
        return f"Admin('{self.username}', '{self.email}', '{self.image_file}')"

class Store(db.Model):
    store_id = db.Column(db.Integer(), primary_key=True)
    store_name = db.Column(db.String(30), unique=True, nullable=False)
    store_location = db.Column(db.String(50), nullable=False)
    store_description = db.Column(db.String(100))
    store_email = db.Column(db.String(60), nullable=False)
    store_contact = db.Column(db.String(60), nullable=False)
    store_logo = db.Column(db.String(60), nullable=False, default='default_store_logo.jpg')

    #refernces back {{store_admin}} property
    user_id = db.Column(db.Integer, db.ForeignKey('admin.id'), nullable=False)
    #each store has many products 1-to-M
    products = db.relationship('Product', backref='store', lazy=True)

    def __repr__(self):
        return f"Store('{self.store_name}', '{self.store_location}', '{self.store_description}','{self.store_email}','{self.store_contact}', '{self.store_logo}')"

class Product(db.Model):
    product_id = db.Column(db.Integer(), primary_key=True)
    product_name = db.Column(db.String(30), unique=True, nullable=False)
    product_description = db.Column(db.String(100))
    product_category = db.Column(db.String(30), nullable=False)
    product_price = db.Column(db.Integer(), nullable=False)
    product_store = db.Column(db.String(30), nullable=False)
    img_product = db.Column(db.String(60), nullable=False, default='default_product.jpg')

    #each product is created for a specific store
    # one store --> Many products relationship M-to-1
    #store id can be referenced back by the Property --> store  | line 63 --> product.store
    store_id = db.Column(db.Integer, db.ForeignKey('store.store_id'), nullable=False)

    #each product falls under a category

    def __repr__(self):
        return f"Product('{self.product_name}', '{self.product_price}','{self.product_category}', '{self.product_store}', '{self.product_description}', '{self.img_product}')"


class Category(db.Model):
    category_id = db.Column(db.Integer(), primary_key=True)
    category_name = db.Column(db.String(30), unique=True, nullable=False)
    category_description = db.Column(db.String(30))
    img_category_product = db.Column(db.String(60), nullable=False, default='default_icon.jpg')

    #A category can be created by any admin, and should be accessible by any other admin
    # one store --> Many products relationship 1-to-M
    #creator = db.Column(db.Integer, db.ForeignKey('admin.id'), nullable=False)

    def __repr__(self):
        return f"Category('{self.category_name}', '{self.img_category_product}')"

          