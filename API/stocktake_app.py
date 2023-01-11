from flask import Flask, render_template, url_for, flash
from forms import Login, Registration

app = Flask(__name__)

app.config['SECRET_KEY'] = '51dae20a00f6f3edee3a23f7fe4907c2'

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
    form = Registration()
    return render_template('dashboard.html', title='dashboard', form=form)


'''----------------------------------App API End Points'''
@app.route("/createlist", methods=["Post"])
def savelist():
    return "<p>Hello, World!</p>"


if __name__ == '__main__':
	app.run(debug=True)
