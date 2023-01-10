from flask import Flask

app = Flask(__name__)


@app.route("/")
def index():
    return "<p>welcome to the index page - def</p>"

@app.route("/login", methods=['GET', 'POST'])
def login():
    return "<p>Hello, World!</p>"
	
@app.route("/register")
def register_new_user():
    return "<p>Hello, World2</p>"
	
@app.route("/createlist", methods=["Post"])
def savelist():
    return "<p>Hello, World!</p>"