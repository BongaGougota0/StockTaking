from flask_wtf import FlaskForm
from flask_login import current_user
from stocktake_app.models import User
from stocktake_app import bcrypt
from flask_wtf.file import FileField, FileAllowed
from wtforms import StringField, PasswordField, SubmitField, FileField, BooleanField, TextAreaField
from wtforms.validators import DataRequired, Length, Email, EqualTo, ValidationError

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
		validators=[DataRequired(), EqualTo('password')])

	register = SubmitField("Register")

	'''Verify input details - they should not be already existing.'''

	def validate_username(self, username):
		user = User.query.filter_by(username=username.data).first()
		if user:
			raise ValidationError('Username not available, try another one.')

	
	def validate_email(self, email):
		user = User.query.filter_by(email=email.data).first()
		if user:
			raise ValidationError('Email already in user, try with another email')

class EditProfileForm(FlaskForm):
	name = StringField("Name")
	username = StringField('Username')
	location = StringField("Location")
	address = StringField('Address')
	contact = StringField('Contact')
	email = StringField('Email')
	picture =FileField("Image", validators=[FileAllowed(['jpeg', 'png', 'jpg'])])

	save_changes = SubmitField('Save Changes')
	change_password = SubmitField('Change Password')



'''Class for login in'''
class Login(FlaskForm):
	email = StringField('Email', validators=[DataRequired(), Email()])
	password = PasswordField('Password',validators=[DataRequired()])
	remember = BooleanField()
	login = SubmitField('Login')

class CreateStoreForm(FlaskForm):
	name = StringField('Store Name', validators=[DataRequired()])
	description = TextAreaField('About Store')
	location = StringField('Store Location')
	email = StringField('Store Email', validators=[DataRequired()])
	contact = StringField('Store Contact', validators=[DataRequired()])
	image_file = FileField('Store Image/Logo', validators=[FileAllowed(['jpg', 'png', 'jpeg'])])
	# auto inserted
	# admin_id = StringField(validators=[DataRequired()])

	create_store = SubmitField('Create Store')

class NewProduct(FlaskForm):
	product_name = StringField('Product Name', validators=[DataRequired()])
	product_description = TextAreaField('Product Description', validators=[DataRequired()])
	product_category = StringField('Product Category', validators=[DataRequired()])
	product_price = StringField('Price', validators=[DataRequired()])
	product_quantity = StringField('In Stock', validators=[DataRequired()])
	product_store = StringField('Store Seller', validators=[DataRequired()])
	image_file = FileField('Store Logo', validators=[FileAllowed(['jpg', 'png', 'jpeg'])])
	# store_id = StringField(validators=[DataRequired()])

	create_button = SubmitField('Create Product')

class EditProductForm(FlaskForm):
	product_name = StringField('Product Name', validators=[DataRequired()])
	product_description = StringField('Your Full Name', validators=[DataRequired()])
	product_category = StringField('Product Category', validators=[DataRequired()])
	product_price = StringField('Price', validators=[DataRequired(), Email()])
	product_quantity = StringField('In Stock', validators=[DataRequired()])
	product_store = StringField('Store Seller', validators=[DataRequired()])
	image_file = FileField('Upload category image', validators=[FileAllowed(['jpg', 'png', 'jpeg'])])
	store_id = StringField(validators=[DataRequired()])

	save_changes = SubmitField('Save Changes')

class NewCategory(FlaskForm):
	category_name = StringField('Product Name', validators=[DataRequired()])
	category_description = StringField('Your Full Name', validators=[DataRequired()])
	image_file = FileField('Upload category image', validators=[FileAllowed(['jpg', 'png', 'jpeg'])])

	create_button = SubmitField('Create Category')