# -*- coding: utf-8 -*-
"""
Created on 
Fri Jan 13 02:54:21 2023
@author: gmvn
"""
from flask import Flask, render_template, url_for, flash, json, redirect, jsonify, request
from stocktake_app import app, bcrypt, db
from flask_login import current_user, logout_user, login_user, login_required
from stocktake_app.forms import Login, Registration, NewProduct
from stocktake_app.models import User, List, Admin, Category, Product, Store, App

'''--------------------------------Site Admin API End Points'''

@app.route("/")
@app.route("/home", methods=["GET"])
def home():
    form=Login()
    return render_template('home.html', title='home', form=form)


@app.route("/myaccout", methods=["GET", "POST"])
def myaccount():
    return render_template('myaccout.html', title='My Account')


@app.route("/dashboard", methods=["GET","POST"])
def dashboard():
    data_my = [40, 92, 45, 32, 34, 52, 41]
    return render_template('dashboard.html', title='Dashboard', my_data = json.dumps(data_my))


@app.route("/add_product", methods=["GET","POST"])
def add_product():
    form = NewProduct()
    return render_template("new_product.html", title='New Product', form=form)


@app.route("/logout")
def logout():
    logout_user()
    form = Login()
    return render_template('logout.html', title='logout', form=form)


@app.route("/login", methods=["GET","POST"])
def login():
    # if current_user.is_authenticated:
    #     return redirect( url_for('dashboard_view'))
    form = Login()
    if form.validate_on_submit():
        '''Verify login details - Correctness.'''
        user = User.query.filter_by(email=form.email.data).first()
        if user and bcrypt.check_password_hash(user.password, form.password.data):
           # flash(f"Login Succesfull!", 'success')
            login_user(user, remember=form.remember.data)
           # next_page = request.args.get('next')
           # return redirect(next_page) if next_page else redirect(url_for('dashboard') )
            return redirect(url_for('dashboard') )
        else:
            flash('Login Unsuccesfull, please check you details', 'danger')
    return render_template('login.html', title='Login', form=form)
	

@app.route("/register", methods=["GET", "POST"])
def register():
    # if current_user.is_authenticated:
    #     return redirect( url_for('dashboard_view'))
    form = Registration()
    print('form object created')
    if form.validate_on_submit():
        print('make user')
        hash_password = bcrypt.generate_password_hash(form.password.data).decode('utf-8')
        user = User(name=form.name.data, username=form.username.data, email=form.email.data, password=hash_password)
        db.session.add(user)
        db.session.commit()
        flash('Account created. Now Login', 'success')
        return redirect( url_for('login'))
    return render_template('register.html', header='Register', title='Create New Account', form=form)


@app.route("editproduct", methods=["GET", "POST"])
def editproduct():
    return render_template('editproduct.html', title='Edit Product')


@app.route('createcategory', methods=["GET", "POST"])
def createcategory():
    return render_template('createcategory.html', title='Create Categoryy')

'''----------------------------------Mobile Application API End Points --- Begin

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
    user = App.query.filter_by(username= username.data).first()
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

    user = App.query.filter_by(username=username.data).first()
    if user:
        return jsonify({'Message':"Username already takekn.",'Status':'Unsuccesful'})
    else:
        em = App.query.filter_by(email=email.data).first()
        if em:
            mssg = validate_email(em.email)
            return jsonify({'Message':mssg,'Status':'Unsuccesful'})
        else:
            user = App(name=name.data, email=email.data, username=username.data,
             location=location.data, password=hashpassword)

        db.session.add(user)
        db.session.commit()
        return jsonify({'Message':'User account created.', 'Status':'Success'})

def validate_username(username):
            user = App.query.filter_by(username=username.data).first()
            if user:
                return False
            else:
                return True

def validate_email(email):
            user = App.query.filter_by(email=email.data).first()
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

    user = App.query.filter_by(username=username.data)

    if user.username != username.data and validate_username(username.data):
        #username available, continue ...
        if user.email != email.data and validate_email(email.data):
            #continue email not used before...
            updateuser = App(name=name.data, email=email.data,username=username.data,
            location=location.data,password=password.data)
            db.session.add(updateuser)
            db.session.commit()
        else:
            return jsonify({'Message':'Email already exists', 'Status':'Unsuccesful'})
    else:
        return jsonify({'Message':'Username already exists', 'Status':'Unsuccesful'})

----------------------------------Mobile Application API End Points --- End'''