# -*- coding: utf-8 -*-
"""
Created on 
Fri Jan 13 02:54:21 2023
@author: gmvn
"""
from flask import Flask, render_template, url_for, flash, json, redirect, jsonify, request, abort
from flask_login import current_user, logout_user, login_user, login_required
from stocktake_app import db, app, bcrypt
from stocktake_app.forms import Login, Registration, NewCategoryForm, AddProduct, EditProfileForm, EditProductForm, CreateStoreForm
from stocktake_app.models import User, List, Admin, Category, Product, Store, App

'''--------------------------------Site Admin API End Points'''


@app.route("/my_account", methods=["GET", "POST"])
@login_required
def my_account():
    form = EditProfileForm()
    if form.validate():
        if form.picture.data:
            picture_file = save_image('static/profile_img/', form.picture.data)
            current_user.image_file = picture_file

        current_user.name = form.name.data
        current_user.username = form.username.data
        current_user.email = form.email.data
        current_user.address = form.address.data
        current_user.contact = form.contact.data
        current_user.location = form.location.data
        current_user.about = form.about.data
        db.session.commit()

        flash('Your account has been updated!', 'success')
        return redirect(url_for('my_account'))
        

    elif request.method == 'GET':
        form.name.data = current_user.name
        form.username.data = current_user.username
        form.email.data = current_user.email
        form.location.data = current_user.location
        form.address.data = current_user.address
        form.contact.data = current_user.contact
        form.about.data = current_user.about
        
    else:
        for _, errors in form.errors.items():
            for error in errors:
                print(error)
    
    return render_template('my_account.html', title='Account',form=form)
    


@app.route("/dashboard", methods=["GET","POST"])
@login_required
def dashboard():

    my_dict1 = {'name':'Sales', 'data':[3, 6, 21, 15, 9, 18, 8]}
    my_dict2 = {'name':'Customers', 'data':[33, 6, 39, 15, 9, 16, 46]}
    my_dict3 = {'name':'Revanue', 'data':[13, 61, 19, 37, 29, 8, 9]}
    set_ = [my_dict1, my_dict2, my_dict3]

    return render_template('dashboard.html', title='Dashboard', my_data = json.dumps(set_))


@app.route("/new_product", methods=['GET', 'POST'])
@login_required
def new_product():
    form = AddProduct()
    store = Store.query.filter_by(user_id=current_user.id).first()

    if store is None:
        flash('You do not have a store to manage/add product','success')
        return redirect( url_for('new_store'))
    
    if form.validate_on_submit():
        
        if form.picture.data:
            picture_file = save_image('static/products_img/', form.picture.data)
            
        product = Product(product_name=form.product_name.data,
        product_description=form.product_description.data, product_category=form.product_category.data,
        product_price=form.product_price.data, product_store=form.product_store.data,
        img_product=picture_file, store_id=store.store_id)
        print('Your products added! success')
        db.session.add(product)
        db.session.commit()
        # current_user.image_file = picture_file
        flash('Your products added!', 'success')
        return redirect(url_for('new_product'))
        
    return render_template('new_product.html', title ="Add Product", form=form)



#method to create store only once
@app.route("/new_store", methods=["GET","POST"])
@login_required
def new_store():
    form = CreateStoreForm()
    image_file = 'default.jpg'

    if form.validate_on_submit():
        if form.picture.data:
            image_file = save_image('static/img/', form.picture.data)

        store = Store(store_name=form.name.data, store_location=form.location.data,
        store_description=form.description.data, store_email=form.email.data, 
        store_contact=form.contact.data, store_logo=image_file, 
        user_id=current_user.id)

        db.session.add(store)
        db.session.commit()

        flash('Store created', 'success')
        return redirect(url_for('new_product'))
    return render_template('new_store.html', title='New Store', form=form)


@app.route("/logout")
def logout():
    logout_user()
    return redirect( url_for('login') )

@app.route("/", methods=["GET","POST"])
@app.route("/login", methods=["GET","POST"])
def login():
    if current_user.is_authenticated:
        return redirect( url_for('dashboard'))
    form = Login()
    if form.validate_on_submit():
        '''Verify login details - Correctness.'''
        user = Admin.query.filter_by(email=form.email.data).first()
        if user and bcrypt.check_password_hash(user.password, form.password.data):
            flash(f"Login Succesfull!", 'success')
            login_user(user, remember=form.remember.data)
            next_page = request.args.get('next')
            # Authorise user to continue to the intended
            #  page else goto default page after login
            return redirect(next_page) if next_page else redirect(url_for('dashboard') )
        else:
            flash('Login Unsuccesfull, please check you details', 'danger')
    return render_template('login.html', title='Login', form=form)
	

@app.route("/register", methods=["GET", "POST"])
def register():
    if current_user.is_authenticated:
        return redirect( url_for('dashboard'))
    form = Registration()
    if form.validate_on_submit():
        hash_password = bcrypt.generate_password_hash(form.password.data).decode('utf-8')
        user = Admin(name=form.name.data, username=form.username.data, 
        email=form.email.data, password=hash_password)
        db.session.add(user)
        db.session.commit()
        flash('Account created. Now Login', 'success')
        return redirect( url_for('login'))
    return render_template('register.html', header='Register', title='Create New Account', form=form)


@app.route("/edit_product", methods=["GET", "POST"])
@login_required
def edit_product():
    form = EditProductForm()
    return render_template('edit_product.html', title='Edit Product', form=form)


@app.route('/create_category', methods=["GET", "POST"])
@login_required
def create_category():
    form = NewCategoryForm()

    if form.validate_on_submit():
        if form.image_file.data:
            image_file = save_image('static/img/', form.image_file.data)
            
        category = Category(category_name=form.category_name.data,
         category_description=form.category_description, image_file=image_file)

        db.session.add(category)
        db.session.commit()
        flash('Category created,now add products.')
        return redirect( url_for('new_products') )

    return render_template('create_category.html', title='Create Category')

import os, secrets
from PIL import Image
def save_image(path, picture_data):
    rand_name = secrets.token_hex(10)
    _, ext = os.path.splitext(picture_data.filename)
    new_filename = rand_name + ext
    file_path = os.path.join(app.root_path, path + new_filename)

    '''resize image here'''
    output_size = (200, 200) # method used by diff. call this will vary
    i = Image.open(picture_data)
    i.thumbnail(output_size)
    i.save(file_path)

    return new_filename


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