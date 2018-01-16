/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thirdParty;

/**
 *
 * @author jjti
 */
public class Chaves {

    private String id;
    private String paraObjeto;
    private String attributeName;
    private String attributeType;

    public Chaves(String id, String paraObjeto, String attributeName, String attributeType) {
        this.id = id;
        this.paraObjeto = paraObjeto;
        this.attributeName = attributeName;
        this.attributeType = attributeType;
    }

    public Chaves() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParaObjeto() {
        return paraObjeto;
    }

    public void setParaObjeto(String paraObjeto) {
        this.paraObjeto = paraObjeto;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

}
