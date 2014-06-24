package com.peoplewearus.web.spring.validation;

import com.peoplewearus.web.spring.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public final class ProductFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product productForm = (Product) target;

        if ("red".equals(productForm.getReferenceNumber())) {
            errors.rejectValue("referenceNumber", "refrerenceNumber.taken");
        }
    }

}