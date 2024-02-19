package com.i.Maper

import com.i.db.NumberEntity
import com.i.number.model.ModelNumberInfo

 fun mapNumberEntityToModelNumberInfo(numberEntity: NumberEntity): ModelNumberInfo {
    return ModelNumberInfo(
        text = numberEntity.text,
        number = numberEntity.number
    )

}
fun mapModelNumberToNumberEntity(modelNumber: ModelNumberInfo): NumberEntity {
    return NumberEntity(
        text = modelNumber.text,
        number = modelNumber.number
    )
}
