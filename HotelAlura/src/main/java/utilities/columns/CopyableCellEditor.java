package utilities.columns;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CopyableCellEditor extends AbstractCellEditor implements TableCellEditor {

    private final JLabel copyLabel;

    public CopyableCellEditor() {
        copyLabel = new JLabel();
        copyLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String value = copyLabel.getText();
                if (value != null && !value.isEmpty()) {
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(new StringSelection(value), null);
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        copyLabel.setText(value != null ? value.toString() : "");
        return copyLabel;
    }

    @Override
    public Object getCellEditorValue() {
        return copyLabel.getText();
    }

}
