/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcc.software;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author pradeep
 */
public class SetTextFieldMethod
extends PlainDocument {
    private int limit;

    public SetTextFieldMethod(int limit) {
        this.setLimit(limit);
    }

    public final int getLimit() {
        return this.limit;
    }

    public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException {
        if (offset < this.limit) {
            super.insertString(offset, s, attributeSet);
        }
    }

    public final void setLimit(int newValue) {
        this.limit = newValue;
    }
}
