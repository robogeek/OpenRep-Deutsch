/*Copyright 2008 by Vladimir Polony, Stupy 24, Banska Bystrica, Slovakia

This file is part of OpenRep FREE homeopathic software.

    OpenRep FREE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    OpenRep FREE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with OpenRep FREE.  If not, see <http://www.gnu.org/licenses/>.*/

/* Opens a new console dialog. Console is used to write and execute commands.
 * 
 *
 * Created on October 7, 2008, 8:25 PM
 */

package prescriber;

import java.util.ArrayList;

/**
 *
 * @author  vladimir
 */
public class ConsoleDialog extends javax.swing.JDialog {
    /** the list of all the previously executed commands*/
    private ArrayList<String> commands = new ArrayList();
    /** pointer to the main form */
    private PrescriberView main_editor;
    /** contains the pointer to the index in the commands list to determine which command is being listed
        by the UP / DOWN keys */
    int list_pointer = 0;
    
    /** Constructor
     * 
     * @param parent
     * @param modal
     * @param main_editor
     */
    public ConsoleDialog(java.awt.Frame parent, boolean modal, PrescriberView main_editor) {
        super(parent, modal);
        initComponents();
        this.main_editor = main_editor;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ConsoleEdit = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        ConsoleEdit.setColumns(20);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(prescriber.PrescriberApp.class).getContext().getResourceMap(ConsoleDialog.class);
        ConsoleEdit.setFont(resourceMap.getFont("ConsoleEdit.font")); // NOI18N
        ConsoleEdit.setForeground(resourceMap.getColor("ConsoleEdit.foreground")); // NOI18N
        ConsoleEdit.setRows(5);
        ConsoleEdit.setName("ConsoleEdit"); // NOI18N
        ConsoleEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ConsoleEditKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(ConsoleEdit);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void ConsoleEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ConsoleEditKeyPressed
  // if back space was pressed and the cursor would go to the previous line -> CANCEL operation
  if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_BACK_SPACE) {
     int pos = ConsoleEdit.getCaretPosition()-1;
     String text = ConsoleEdit.getText();
     if (pos == -1 || text.charAt(pos) == '\n' || text.charAt(pos) == '\r') evt.setKeyCode(java.awt.event.KeyEvent.VK_UNDEFINED);
  }
  
  // if UP key was pressed, list the previous command
  if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
       evt.setKeyCode(java.awt.event.KeyEvent.VK_UNDEFINED);          
       if (list_pointer >= 1) {
           ConsoleEdit.setText(ConsoleEdit.getText().substring(0, ConsoleEdit.getText().lastIndexOf("\n")) + "\n"+ commands.get(list_pointer-1));
           list_pointer--;           
       }
   }
   else
   // if ENTER was pressed, execute the command
   if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {       
       if (ConsoleEdit.getText().lastIndexOf("\n") == -1) commands.add(ConsoleEdit.getText());
       else
       commands.add(ConsoleEdit.getText().substring(ConsoleEdit.getText().lastIndexOf("\n")+1));
       main_editor.ExecuteCommand(commands.get(commands.size() - 1), this);
       list_pointer = commands.size();
   }
   else
   // if DOWN key was pressed list the next command
   if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
       evt.setKeyCode(java.awt.event.KeyEvent.VK_UNDEFINED);          
       if (list_pointer < commands.size()) {
           ConsoleEdit.setText(ConsoleEdit.getText().substring(0, ConsoleEdit.getText().lastIndexOf("\n")+1) + commands.get(list_pointer+1));
           list_pointer++;           
       }
   } 
   
}//GEN-LAST:event_ConsoleEditKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea ConsoleEdit;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}