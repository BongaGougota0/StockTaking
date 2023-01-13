from flask_wtf import FlaskForm
from wtforms import StringField, PasswordField, SubmitField
from wtforms.validators import DataRequired, Length, Email, EqualTo

'''Class for client registration'''
class Registration(FlaskForm):
	name = StringField('Your Full Name', validators=[DataRequired()])
	username = StringField('Username',
	 validators=[DataRequired(), Length(min=2, max=15)])
	email = StringField('Email'
		, validators=[DataRequired(), Email()])

	password = PasswordField('Password',
		validators=[DataRequired()])

	confirm_password = PasswordField('Confirm Password',
		validators=[DataRequired(), EqualTo(password)])

	submitButton = SubmitField("Sign Up")

'''Class for login in'''
class Login(FlaskForm):
	email = StringField('Email'
		, validators=[DataRequired(), Email()])

	password = PasswordField('Password',
		validators=[DataRequired()])

	loginButton = SubmitField('Login')
