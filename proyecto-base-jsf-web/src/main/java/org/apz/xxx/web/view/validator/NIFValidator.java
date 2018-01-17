package org.apz.xxx.web.view.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang.StringUtils;
import org.apz.xxx.web.view.util.AppConstantes;
import org.apz.xxx.web.view.util.UtilViews;

public class NIFValidator implements Validator {

          
	public void validate(FacesContext contex,UIComponent component, Object value) throws ValidatorException {
        
		try {
			String valor = StringUtils.defaultString((String)value);
	              // el valor debe tener 9 posiciones, de las cuales las primeras deben ser dígitos y la última letra
	        valor=valor.toUpperCase();
	        Pattern mask =  Pattern.compile("[0-9]{8,8}[A-Z]");
	        Matcher matcher = mask.matcher(valor);
	        if (!matcher.matches()) {
	        	UtilViews.addMensajeError(AppConstantes.ALERTA_DNI_INCORRECTO, value);
		    }
	        String dni=valor.substring(0,8);
	        String digitoControl = valor.substring(8,9);
	        // Calculamos la letra de control
	        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
	        int posicion_modulo = Integer.parseInt(dni)%23;
	        String digitoControlCalculado = letras.substring(posicion_modulo,posicion_modulo+1);
	        
	        if (!digitoControl.equalsIgnoreCase(digitoControlCalculado)) {
	        	UtilViews.addMensajeError(AppConstantes.ALERTA_DNI_INCORRECTO, value);
	        }
	    } catch (Exception e) {
	    	UtilViews.addMensajeError(AppConstantes.ALERTA_DNI_INCORRECTO, value);
		} 
	
	}
}	