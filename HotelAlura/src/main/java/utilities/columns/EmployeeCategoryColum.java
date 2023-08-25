package utilities.columns;

import utilities.enums.EmployeeCategory;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class EmployeeCategoryColum extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private final JComboBox<EmployeeCategory> comboBox;
    private EmployeeCategory selectedCategory;

    public EmployeeCategoryColum() {
        comboBox = new JComboBox<>(EmployeeCategory.values());
        comboBox.addActionListener(e -> stopCellEditing());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        selectedCategory = (EmployeeCategory) value;
        comboBox.setSelectedItem(selectedCategory);
        return comboBox;
    }

    @Override
    public Object getCellEditorValue() {
        return comboBox.getSelectedItem();
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        selectedCategory = (EmployeeCategory) value;
        comboBox.setSelectedItem(selectedCategory);
        return comboBox;
    }
}
