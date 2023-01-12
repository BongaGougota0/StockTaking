from flask import Flask, render_template, url_for, flash, json, redirect
from forms import Login, Registration
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime

app = Flask(__name__)

app.config['SECRET_KEY'] = '51dae20a00f6f3edee3a23f7fe4907c2'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///mysite.db'

#database instance
db = SQLAlchemy(app)

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


if __name__ == '__main__':
	app.run(debug=True)
