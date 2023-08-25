package utilities.columns;

import utilities.enums.PaymentMethod;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class PaymentMethodColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private final JComboBox<PaymentMethod> comboBox;
    private PaymentMethod selectedPaymentMethod;

    public PaymentMethodColumn() {
        comboBox = new JComboBox<>(PaymentMethod.values());
        comboBox.addActionListener(e -> stopCellEditing());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        selectedPaymentMethod = (PaymentMethod) value;
        comboBox.setSelectedItem(selectedPaymentMethod);
        return comboBox;
    }

    @Override
    public Object getCellEditorValue() {
        return comboBox.getSelectedItem();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        selectedPaymentMethod = (PaymentMethod) value;
        comboBox.setSelectedItem(selectedPaymentMethod);
        return comboBox;
    }
}
