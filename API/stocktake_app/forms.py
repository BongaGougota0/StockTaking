from flask_wtf import FlaskForm
from flask_login import current_user
from stocktake_app.models import User, Admin
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
		user = Admin.query.filter_by(username=username.data).first()
		if user:
			raise ValidationError('Username not available, try another one.')

	
	def validate_email(self, email):
		user = Admin.query.filter_by(email=email.data).first()
		if user:
			raise ValidationError('Email already in user, try with another email')

class EditProfileForm(FlaskForm):
	location = StringField('Location')
	contact = StringField('Contact')
	address = StringField('Address')
	email = StringField("Email")
	username = StringField("Username")
	picture = FileField('Update Profile Picture', validators=[FileAllowed(['jpeg', 'png', 'jpg'])])
	name = StringField("Name")
	about = TextAreaField("About Store")

	save_changes = SubmitField('Save Changes')

	#verify changes before pushing to database
	def validate_username(self, username):
		if username.data != current_user.username:
			user = Admin.query.filter_by(username=username.data).first()
			if user:
				raise ValidationError('Username taken, try another one.')
	
	def validate_email(self, email):
		if email.data != current_user.email:
			user = Admin.query.filter_by(email=email.data).first()
			if user:
				raise ValidationError('Email taken, try another one.')



'''Class for login in'''
class Login(FlaskForm):
	email = StringField('Email', validators=[DataRequired(), Email()])
	password = PasswordField('Password',validators=[DataRequired()])
	remember = BooleanField()
	login = SubmitField('Login')

class CreateStoreForm(FlaskForm):
	name = StringField('Store Name', validators=[DataRequired()])
	description = StringField('About Store')
	location = StringField('Store Location')
	email = StringField('Store Email', validators=[DataRequired()])
	contact = StringField('Store Contact', validators=[DataRequired()])
	picture = FileField('Store Image/Logo', validators=[FileAllowed(['jpg', 'png', 'jpeg'])])

	create_store = SubmitField('Create Store')

class AddProduct(FlaskForm):
	product_name = StringField('Product Name', validators=[DataRequired()])
	product_description = TextAreaField('Product Description', validators=[DataRequired()])
	product_category = StringField('Product Category', validators=[DataRequired()])
	product_price = StringField('Price', validators=[DataRequired()])
	product_quantity = StringField('In Stock', validators=[DataRequired()])
	product_store = StringField('Store Seller', validators=[DataRequired()])
	picture = FileField('Product Image', validators=[FileAllowed(['jpg', 'png', 'jpeg'])])
	# store_id = StringField(validators=[DataRequired()])

	create_button = SubmitField('Create Product')

class EditProductForm(FlaskForm):
	product_name = StringField('Product Name', validators=[DataRequired()])
	product_description = StringField('About Product', validators=[DataRequired()])
	product_category = StringField('Product Category', validators=[DataRequired()])
	product_price = StringField('Price', validators=[DataRequired(), Email()])
	product_quantity = StringField('In Stock', validators=[DataRequired()])
	product_store = StringField('Store Seller', validators=[DataRequired()])
	image_file = FileField('Upload category image', validators=[FileAllowed(['jpg', 'png', 'jpeg'])])
	# store_id = StringField(validators=[DataRequired()])

	save_changes = SubmitField('Save Changes')

class NewCategoryForm(FlaskForm):
	category_name = StringField('Product Name', validators=[DataRequired()])
	category_description = StringField('Your Full Name', validators=[DataRequired()])
	image_file = FileField('Upload category image', validators=[FileAllowed(['jpg', 'png', 'jpeg'])])

	create_button = SubmitField('Create Category')

class PasswordChangeForm(FlaskForm):
	current_password = StringField("Current Password", validators=[DataRequired()])
	new_password = StringField("New Password", validators=[DataRequired()])
	confirm_password = StringField("Confirm Password", validators=[DataRequired()])

	change_password = SubmitField("Change Password", validators=[EqualTo('new_password')])