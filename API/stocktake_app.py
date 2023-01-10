from flask import Flask

app = Flask(__name__)

@app.route("/login")
def login():
    return "<p>Hello, World!</p>"
	
@app.route("/register")
def register():
    return "<p>Hello, World!</p>"
	
@app.route("/createlist", methods=["Post"])
def register():
    return "<p>Hello, World!</p>"