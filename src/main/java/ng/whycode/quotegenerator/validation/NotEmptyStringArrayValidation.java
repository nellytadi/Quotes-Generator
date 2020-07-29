package ng.whycode.quotegenerator.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class NotEmptyStringArrayValidation implements ConstraintValidator<EmptyArrayString, String[]> {
	
	
	@Override
    public void initialize(EmptyArrayString arrayString) {
    }
	
	
	@Override
	public boolean isValid(String [] value, ConstraintValidatorContext context) {

		if (value == null || value.length == 0)
			return false;
		return true;
	}

	

}
