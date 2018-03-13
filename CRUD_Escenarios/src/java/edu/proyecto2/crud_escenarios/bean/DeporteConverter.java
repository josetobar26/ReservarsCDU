/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.bean;

import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.data.DeporteModel;
import java.lang.annotation.Annotation;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author jose
 */
@FacesConverter("deporteConverter")
public class DeporteConverter  implements Converter{

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uic, String value) {
      
        if(value != null && value.trim().length() > 0) {
            try {
                 ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{deporteBean}", DeporteBean.class);
               DeporteBean deportes= (DeporteBean)vex.getValue(ctx.getELContext());
              
               return deportes.getDeport(Integer.valueOf(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object == null) {
        
            
         return "";
        }
        if (object instanceof Deporte) {
            return String.valueOf(((Deporte)object).getIdDeporte() );
        }
        else { 
            return "";
        }
    }

}
