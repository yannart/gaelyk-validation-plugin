package com.yannart.validation

import java.lang.annotation.*
import javax.validation.constraints.*

class JSR303ToJson {

	def static validatorAnnotations = [new ValNotNull(), new ValSize(), new ValMin(), new ValMax()]

	static String getJsonValidationRules(Class clazz){
		
		def fieldMap = [:]
		
		clazz.declaredFields.each {	field ->
		
			def fieldAttributeMap = [:]
			
			validatorAnnotations.each { validator ->
				
				def annotation = field.getAnnotation(validator.annotation)
				
				if(annotation != null){
					validator.setAttributes(annotation, fieldAttributeMap)
				}
			}
			
			if(fieldAttributeMap.size() > 0) {
				fieldMap.put(field.name, fieldAttributeMap)
			}
		}
		
		return renderJSON(fieldMap)
	}
	
	static String renderJSON(def fieldMap) {
		def resultJson = "rules: {\r\n"
		
		def fieldNum = 0
		
		fieldMap.each{ fieldName, fieldAttributeMap ->
			
			if(fieldNum > 0) resultJson += ",\r\n"
			resultJson += "\t${fieldName}: {\r\n"
			
			def constraintNum = 0
			fieldAttributeMap.each {attribute, value ->
				if(constraintNum > 0) {
					resultJson += ",\r\n"
				}
				
				resultJson += "\t\t${attribute}: ${value}"
				constraintNum++
			}
			
			resultJson += "\r\n\t}"
			fieldNum++
		}
		
		resultJson += "\r\n}"
	}
}

interface ValConstraint {
	Class getAnnotation()
	void setAttributes(def annotation, def fieldAttributeMap)
}

class ValSize implements ValConstraint{
	
	Class getAnnotation() {
		return Size.class
	}
	
	void setAttributes(def size, def fieldAttributeMap) {
		
		if(size.min()) {
			fieldAttributeMap.put("minlength", size.min())
		}
		
		if(size.max()) {
			fieldAttributeMap.put("maxlength", size.max())
		}
	}
}

class ValMin implements ValConstraint{
	
	Class getAnnotation() {
		return Min.class
	}
	
	void setAttributes(def min, def fieldAttributeMap) {
		
		if(min.value()) {
			fieldAttributeMap.put("min",min.value())
		}
	}
}

class ValMax implements ValConstraint{
	
	Class getAnnotation() {
		return Max.class
	}
	
	void setAttributes(def max, def fieldAttributeMap) {
		
		if(max.value()) {
			fieldAttributeMap.put("max",max.value())
		}
	}
}

class ValNotNull implements ValConstraint{
	
	Class getAnnotation() {
		return NotNull.class
	}
	
	void setAttributes(def notNull, def fieldAttributeMap) {
		
		fieldAttributeMap.put("required","true")
	}
}
