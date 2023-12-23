package org.sopt.kotlinsemina.domain.post.model

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class TitleValidator(
    var pattern: String
): ConstraintValidator<ValidTitle, String> {

    override fun initialize(constraintAnnotation: ValidTitle) {
        this.pattern = constraintAnnotation.pattern
    }

    override fun isValid(title: String, context: ConstraintValidatorContext?): Boolean {
        if (title.isBlank()) {
            return false
        }

        if (title.isEmpty() || title.length >= 10) {
            return false
        }

        return title[0] != ' '
    }
}