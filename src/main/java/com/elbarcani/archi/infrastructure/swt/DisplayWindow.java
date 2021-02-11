package com.elbarcani.archi.infrastructure.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.util.Arrays;
import java.util.Optional;

public class DisplayWindow {
    private final Display display;
    private final Shell shell;

    private static final String CANCEL_BTTN = "Cancel";
    private static final String OK_BTTN = "Ok";
    private static final String RESET_BTTN = "Home";


    private static final int DEFAULT_BOTTOM_HORIZONTAL_SPACING = 20;
    private static final int DEFAULT_BOTTOM_MARGIN_BOTTOM = 30;
    private static final int DEFAULT_BOTTOM_MARGIN_TOP = 10;
    private static final int DEFAULT_MAIN_COMPOSITE_MINIMUM_WIDTH = 500;
    private static final int DEFAULT_MARGIN_HEIGHT = 5;
    private static final int DEFAULT_MARGIN_WIDTH = 15;
    private static final int DEFAULT_TOP_MARGIN_HEIGHT = 25;

    protected static final String DEFAULT_FONT_NAME = "Liberation Sans";

    protected Composite titleComposite;
    protected Label titleLbl;
    protected Composite mainComposite;
    protected Composite buttonComposite;
    protected Rectangle displayArea;
    protected Button okBttn;
    protected Button cancelBttn;
    protected Button resetBtn;

    Color DEFAULT_BAR_BACKGROUND_COLOR;
    Color DEFAULT_BOTTOM_BACKGROUND_COLOR;
    Color DEFAULT_MAIN_BACKGROUND_COLOR;
    Color DEFAULT_TOP_BACKGROUND_COLOR;
    Color DEFAULT_TITLE_FOREGROUND_COLOR;

    public DisplayWindow() {
        display = new Display();
        shell = new Shell(display);
        createControls();
        addListeners();
    }

    public Shell getShell(){
        return shell;
    }

    public Button getResetBtn() {
        return resetBtn;
    }

    protected void createControls() {
        createLayout();
        initColors();
        createTitleComposite(shell);
        createMainComposite(shell);
        createButtonComposite(shell);
    }

    private void initColors() {
        DEFAULT_BAR_BACKGROUND_COLOR = new Color(display, 255, 255, 255);
        DEFAULT_BOTTOM_BACKGROUND_COLOR = new Color(display, 255, 255, 255);
        DEFAULT_MAIN_BACKGROUND_COLOR = new Color(display, 255, 255, 255);
        DEFAULT_TOP_BACKGROUND_COLOR = new Color(display, 120, 39, 69);
        DEFAULT_TITLE_FOREGROUND_COLOR = new Color(display, 255, 255, 255);
    }

    protected void createLayout() {
        createButtonCompositeLayout(2);
        GridLayout layout = new GridLayout();
        shell.setLayout(layout);
        shell.setLayoutData(new GridData(GridData.FILL_BOTH));
        shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
    }

    protected void updateDisplayArea(Rectangle displayArea) {
        if (displayArea != null) {
            shell.setBounds(displayArea);
        }
    }

    protected void createTitleComposite(Composite parent) {
        titleComposite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginWidth = DEFAULT_MARGIN_WIDTH;
        layout.marginHeight = DEFAULT_TOP_MARGIN_HEIGHT;
        titleComposite.setLayout(layout);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        titleComposite.setLayoutData(data);
        titleComposite.setBackground(DEFAULT_TOP_BACKGROUND_COLOR);

        titleLbl = new Label(titleComposite, SWT.NONE);
        titleLbl.setFont(new Font(display, DEFAULT_FONT_NAME, 13, SWT.BOLD));
        titleLbl.setForeground(DEFAULT_TITLE_FOREGROUND_COLOR);
    }

    protected void createMainComposite(Composite parent) {
        mainComposite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginWidth = DEFAULT_MARGIN_WIDTH;
        layout.marginHeight = DEFAULT_MARGIN_HEIGHT + 15;
        mainComposite.setLayout(layout);
        mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        mainComposite.setBackground(DEFAULT_MAIN_BACKGROUND_COLOR);
    }

    public Composite getTitleComposite() {
        return titleComposite;
    }

    public Composite getMainComposite() {
        return mainComposite;
    }

    public Composite getButtonComposite() {
        return buttonComposite;
    }

    public Button getOkBttn() {
        return okBttn;
    }

    public void createButtonComposite(Composite parent) {
        buttonComposite = new Composite(parent, SWT.NONE);

        GridLayout layout = new GridLayout(3, false);
        layout.marginWidth = DEFAULT_MARGIN_WIDTH;
        layout.marginBottom = DEFAULT_BOTTOM_MARGIN_BOTTOM;
        layout.marginTop = DEFAULT_BOTTOM_MARGIN_TOP;
        layout.horizontalSpacing = DEFAULT_BOTTOM_HORIZONTAL_SPACING;

        buttonComposite.setLayout(layout);
        buttonComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        buttonComposite.setBackground(mainComposite.getBackground());

        createButtons(buttonComposite);
    }

    protected void createButtons(Composite parent) {
        createResetButton(buttonComposite);
        createCancelButton(buttonComposite);
        createOkButton(buttonComposite);
    }

    protected void createResetButton(Composite parent) {
        resetBtn = new Button(parent, SWT.NONE);
        resetBtn.setText(RESET_BTTN);
        resetBtn.setVisible(false);
    }

    protected void createCancelButton(Composite parent) {
        cancelBttn = new Button(parent, SWT.NONE);
        cancelBttn.setText(CANCEL_BTTN);
    }

    protected void createOkButton(Composite parent) {
        okBttn = new Button(parent, SWT.NONE);
        okBttn.setText(OK_BTTN);
        okBttn.setEnabled(false);
    }

    protected GridLayout createButtonCompositeLayout(int columns) {
        GridLayout layout = new GridLayout(columns, false);
        layout.marginWidth = DEFAULT_MARGIN_WIDTH;
        layout.marginBottom = DEFAULT_BOTTOM_MARGIN_BOTTOM;
        layout.marginTop = DEFAULT_BOTTOM_MARGIN_TOP;
        layout.horizontalSpacing = DEFAULT_BOTTOM_HORIZONTAL_SPACING;
        return layout;
    }
    //====================================================================================================
    // Listener methods
    //====================================================================================================

    protected void addListeners() {
        okBttn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                okPressed();
            }
        });
        cancelBttn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                cancelPressed();
            }
        });
    }

    //====================================================================================================
    // Pressed methods
    //====================================================================================================

    protected void okPressed(){};

    protected void cancelPressed() {
        dispose();
    }

    //====================================================================================================
    // Dispose methods
    //====================================================================================================

    public void dispose() {
        titleComposite.dispose();
        titleComposite = null;
        titleLbl.dispose();
        titleLbl = null;
        mainComposite.dispose();
        mainComposite = null;
        buttonComposite.dispose();
        buttonComposite = null;
        okBttn.dispose();
        okBttn = null;
        cancelBttn.dispose();
        cancelBttn = null;

        if (resetBtn != null) {
            resetBtn.dispose();
        }
        resetBtn = null;
        display.dispose();
        shell.dispose();
    }

    //====================================================================================================
    // Setter methods
    //====================================================================================================

    public void setTitle(String title) {
        titleLbl.setText(title);
    }

    //====================================================================================================
    // Getter methods
    //====================================================================================================

    public String getTitle() {
        return titleLbl.getText();
    }

    //====================================================================================================
    // Utility methods
    //====================================================================================================

    public void open() {
        Point size = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        Rectangle rec = buildDisplayArea(size.x, size.y);
        updateDisplayArea(rec);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
    }

    private Rectangle buildDisplayArea(int width, int height) {
        if (displayArea == null) {
            width = Math.max(width, DEFAULT_MAIN_COMPOSITE_MINIMUM_WIDTH);
        } else {
            width = Math.max(displayArea.width, DEFAULT_MAIN_COMPOSITE_MINIMUM_WIDTH);
            height = displayArea.height;
        }

        // Center the dialog in the active monitor
        Optional<Monitor> activeMonitor = getActiveMonitor(shell);
        Rectangle rect = activeMonitor.map(Monitor::getBounds).orElseGet(shell::getBounds);
        width = Math.min(width, rect.width);
        shell.setSize(width, height);
        Point centerPoint = getCenterPoint(shell, rect);
        return new Rectangle(centerPoint.x, centerPoint.y, width, height);
    }

    private Optional<Monitor> getActiveMonitor(Shell shell) {
        Display display = shell.getDisplay();
        // the cursor position defines the monitor used to display data
        Point cursor = display.getCursorLocation();
        return Arrays.stream(display.getMonitors()).filter(monitor -> monitor.getBounds().contains(cursor))
                .findFirst();
    }

    private Point getCenterPoint(Shell shell, Rectangle bounds) {
        Point shellSize = shell.getSize();
        int freeWidth = bounds.width - shellSize.x;
        int freeHeight = bounds.height - shellSize.y;
        int centerX = bounds.x + (int) (freeWidth / 2f);
        int centerY = bounds.y + (int) (freeHeight / 2f);
        return new Point(centerX, centerY);
    }
}
