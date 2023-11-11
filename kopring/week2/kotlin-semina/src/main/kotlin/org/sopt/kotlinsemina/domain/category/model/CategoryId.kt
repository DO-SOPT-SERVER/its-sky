package org.sopt.kotlinsemina.domain.category.model

import jakarta.persistence.Embeddable
import lombok.EqualsAndHashCode
import java.io.Serializable

@Embeddable
@EqualsAndHashCode
class CategoryId(val id: Short) : Serializable {

}