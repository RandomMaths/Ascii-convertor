import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;
import javax.swing.border.*;

public class GUI implements ActionListener, FocusListener, DocumentListener, ItemListener{
	
	private JLabel path, output, name, line, line2, errorStatus, emptyStatus, approvedStatus;
	private JButton browseButton, outputBrowseButton, approveButton;
	private JTextField browseField, outputBrowseField, nameField;
	private ImageIcon completedIcon, warningIcon, errorIcon;
	private BufferedImage icon;
	private JRadioButton merge;
	private JCheckBox status;
	private JFrame frame;
	private boolean emptyBrowseField, emptyOutputField, emptyNameField, wrongPath, wrongOutputPath, allFeildsCorrect;
	private File inputFile, outputFolder;
	
	public GUI(){
		initialize();
		setSize();
		setLocation();
		addListeners();
		setFocusability();
		adjust();
		
		update();
		frame.setIconImage(icon);
		frame.add(status);
		frame.add(errorStatus);
		frame.add(emptyStatus);
		frame.add(approvedStatus);
		frame.add(line);
		frame.add(merge);
		frame.add(line2);
		frame.add(path);
		frame.add(browseField);
		frame.add(browseButton);
		frame.add(output);
		frame.add(outputBrowseField);
		frame.add(outputBrowseButton);
		frame.add(name);
		frame.add(nameField);
		frame.add(approveButton);
		frame.add(new JLabel());
		
		frame.setTitle("Ascii convertor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public void initialize(){
		frame = new JFrame();
		
		path = new JLabel("Path:");
		browseField = new JTextField(16);
		browseButton = new JButton("Browse");
		
		output = new JLabel("Output:");
		outputBrowseField = new JTextField(16);
		outputBrowseButton = new JButton("Browse");
		
		name = new JLabel("Name:");
		nameField = new JTextField(10);
		
		approveButton = new JButton("Convert");
		
		status = new JCheckBox("Status");
		errorStatus = new JLabel("");
		emptyStatus = new JLabel("");
		approvedStatus = new JLabel("");
		
		line = new JLabel("|");
		merge = new JRadioButton();
		line2 = new JLabel("|");
		
		try{
			icon = ImageIO.read(this.getClass().getResourceAsStream("Icon.png"));
		}catch(IOException e){System.out.println("File not found LOL!!");}
		completedIcon = new ImageIcon("completed-icon.jpg");
		warningIcon = new ImageIcon("warning-icon.jpg");
		errorIcon = new ImageIcon("error-icon.png");
	}
	
	public void setSize(){
		frame.setSize(300, 200);
		
		path.setSize(32,20);
		browseField.setSize(120,20);
		browseButton.setSize(60,19);
		
		output.setSize(45,20);
		outputBrowseField.setSize(120,20);
		outputBrowseButton.setSize(60,19);
		
		name.setSize(40,20);
		nameField.setSize(120,20);
		
		approveButton.setSize(70,25);
		status.setSize(75, 20);
		
		line.setSize(5,12);
		merge.setSize(20,20);
		line2.setSize(5,12);
	}
	
	public void setLocation(){
		path.setLocation((int)((frame.getWidth()/2)-((browseField.getWidth()+path.getWidth()+browseButton.getWidth())/2)-7), (int) ((frame.getHeight()/5)));
		browseField.setLocation((int)(path.getX()+path.getWidth()),(int)path.getY());
		browseButton.setLocation((int)(browseField.getX()+browseField.getWidth()),(int)browseField.getY());
		
		output.setLocation((int) ((frame.getWidth()/2)-((output.getWidth()+outputBrowseField.getWidth()+outputBrowseButton.getWidth())/2)-7),(int) (path.getHeight()+5+path.getY()));
		outputBrowseField.setLocation((int) (output.getX()+output.getWidth()),(int) (path.getHeight()+5+path.getY()));
		outputBrowseButton.setLocation((int) (outputBrowseField.getX()+outputBrowseField.getWidth()),(int) (path.getHeight()+5+path.getY()));
		
		name.setLocation((int) ((frame.getWidth()/2)-((name.getWidth()+nameField.getWidth())/2)-7), (int) (output.getHeight()+5+output.getY()));
		nameField.setLocation((int) (name.getX()+name.getWidth()-5),(int)name.getY());
		
		approveButton.setLocation((int) ((frame.getWidth()/2) - (approveButton.getWidth()/2)-5), (int) (name.getHeight()+5+name.getY()));
		status.setLocation(frame.getWidth() - status.getWidth(), frame.getHeight() - status.getHeight() - 40);
		
		line.setLocation((int) (frame.getWidth()-30-(line.getWidth()/2)),(int) (browseField.getY()+1));
		merge.setLocation((int) (line.getX()-(merge.getWidth()/2)+6),(int) (line.getY()+line.getHeight()));
		line2.setLocation((int) (frame.getWidth()-30-(line2.getWidth()/2)),(int) (merge.getY()+merge.getHeight()-1));
	}
	
	public void addListeners(){
		//ActionListeners
		browseButton.addActionListener(this);
		outputBrowseButton.addActionListener(this);
		approveButton.addActionListener(this);
		//FocusListeners
		browseField.addFocusListener(this);
		outputBrowseField.addFocusListener(this);
		nameField.addFocusListener(this);
		//DocumentListeners
		browseField.getDocument().addDocumentListener(this);
		outputBrowseField.getDocument().addDocumentListener(this);
		nameField.getDocument().addDocumentListener(this);
		//ItemListeners
		merge.addItemListener(this);
		status.addItemListener(this);		
	}
	
	public void setFocusability(){
		status.setFocusable(false);
		browseButton.setFocusable(false);
		outputBrowseButton.setFocusable(false);
		approveButton.setFocusable(false);
	}
	
	public void adjust(){
		//Margin
		path.setBorder(new EmptyBorder(1, 1, 1, 1));
		browseButton.setMargin(new Insets(1, 1, 1, 1));
		output.setBorder(new EmptyBorder(1, 1, 1, 1));
		outputBrowseButton.setMargin(new Insets(1, 1, 1, 1));
		name.setBorder(new EmptyBorder(1, 1, 1, 1));
		approveButton.setMargin(new Insets(5, 5, 5, 5));
		line.setBorder(new EmptyBorder(1, 1, 1, 1));
		merge.setBorder(new EmptyBorder(0, 0, 0, 0));
		line2.setBorder(new EmptyBorder(1, 1, 1, 1));
		
		//Font
		line.setFont(new Font("Arial", Font.PLAIN, 12));
		line2.setFont(new Font("Arial", Font.PLAIN, 12));
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e){}
	}
	
	public void update(){
		updateFields();
		checkAllFields();
		statusChecker();
	}
	
	public void statusChecker(){
		if(status.isSelected()){
			updateStatus();			
		} else if(!status.isSelected()){
			clearStatus("clearAll");
		}
	}
	
	public void updateStatus(){
		boolean errorBrowseField = wrongPath && !emptyBrowseField;
		boolean errorOutputField = wrongOutputPath && !emptyOutputField;
		if(errorBrowseField || errorOutputField){
			errorStatus.setIcon(new ImageIcon(errorIcon.getImage().getScaledInstance(12, 12, Image.SCALE_AREA_AVERAGING)));
			errorStatus.setText("<html>Please Enter Valid Path at" + (errorBrowseField?" <b>Path</b>":"") + (errorOutputField?" <b>Output</b></html>":""));
			errorStatus.setSize(250, 12);
			errorStatus.setForeground(Color.RED);
			errorStatus.setFont(new Font("Arial", Font.PLAIN, 12));
			errorStatus.setLocation(0,0);
		} else {
			clearStatus("clearError");
		}
		
		if(emptyBrowseField || emptyOutputField || emptyNameField){
			emptyStatus.setIcon(new ImageIcon(warningIcon.getImage().getScaledInstance(12, 12, Image.SCALE_AREA_AVERAGING)));
			emptyStatus.setText("<html>Empty Fields at" + (emptyBrowseField?" <b>Path</b>":"") + (emptyOutputField?" <b>Output</b>":"") + (emptyNameField?" <b>Name</b></html>":""));
			emptyStatus.setSize(250, 12);
			emptyStatus.setForeground(new Color(226,121,0).brighter());
			emptyStatus.setFont(new Font("Arial", Font.PLAIN, 12));
			emptyStatus.setLocation(0,0+errorStatus.getHeight());
		} else {
			clearStatus("clearWarnings");
		}
		
		if(allFeildsCorrect){
			approvedStatus.setIcon(new ImageIcon(completedIcon.getImage().getScaledInstance(12, 12, Image.SCALE_AREA_AVERAGING)));
			approvedStatus.setText("All fields correctly filled");
			approvedStatus.setSize(250, 12);
			approvedStatus.setForeground(Color.GREEN);
			approvedStatus.setFont(new Font("Arial", Font.PLAIN, 12));
			approvedStatus.setLocation(0,0+errorStatus.getHeight()+emptyStatus.getHeight());
		} else {
			clearStatus("clearApproved");
		}
	}
	
	public void clearStatus(String toBeCleared){
		switch(toBeCleared){
			case "clearError" : errorStatus.setText("");
								errorStatus.setSize(0,0);
								errorStatus.setIcon(null);
								break;
			case  "clearWarnings" : emptyStatus.setText("");
									emptyStatus.setSize(0,0);
									emptyStatus.setIcon(null);
									break;
			case  "clearApproved" : approvedStatus.setText("");
									approvedStatus.setSize(0,0);
									approvedStatus.setIcon(null);
									break;
			case "clearAll" : clearStatus("clearError");
							  clearStatus("clearWarnings");
							  clearStatus("clearApproved");
							  break;
			default : System.out.println("No KeyWord found!!");
		}
		
	}
	
	public void checkAllFields(){
		inputFile = new File(browseField.getText());
		outputFolder = new File(outputBrowseField.getText());
		
		wrongPath = !inputFile.exists();
		wrongOutputPath = !outputFolder.isDirectory();
		
		emptyBrowseField = browseField.getText().equals("") || browseField.getText().equals("Browse");
		emptyOutputField = outputBrowseField.getText().equals("") || outputBrowseField.getText().equals("Browse");
		emptyNameField = nameField.getText().equals("") || nameField.getText().equals("FileName");
		
		allFeildsCorrect = inputFile.exists() && outputFolder.isDirectory() && !emptyNameField;
	}
	
	public void updateFields(){
		if(merge.isSelected()){
			outputBrowseField.getDocument().removeDocumentListener(this);
			outputBrowseField.setEditable(false);
			outputBrowseField.setText(getDirectory());
			outputBrowseField.setForeground(Color.GRAY);
			outputBrowseButton.setEnabled(false);
		}else if(!merge.isSelected()){
			outputBrowseField.setEditable(true);
			outputBrowseField.setForeground(outputBrowseField.getForeground());
			outputBrowseField.getDocument().removeDocumentListener(this);
			outputBrowseField.getDocument().addDocumentListener(this);
			outputBrowseButton.setEnabled(true);
		}
	}
	
	public String getDirectory(){
		File f = new File(browseField.getText());
		if(f.exists()){
			return f.getAbsoluteFile().getParent();
		}
		return browseField.getText();
	}
	
	@Override
	public void itemStateChanged(ItemEvent ie) {update();}
	
	@Override
	public void changedUpdate(DocumentEvent de) {}
	
	@Override
	public void removeUpdate(DocumentEvent de){
		update();
		if(de.getDocument()==outputBrowseField.getDocument())
			outputBrowseField.setForeground(Color.BLACK);
	}
	
	@Override
	public void insertUpdate(DocumentEvent de){
		update();
		if(de.getDocument()==outputBrowseField.getDocument())
			outputBrowseField.setForeground(Color.BLACK);
	}
	
	@Override
	public void focusGained(FocusEvent evt){
		if(browseField.getText().equals("Browse") && evt.getSource()==browseField){
			browseField.setText("");
			browseField.setForeground(Color.BLACK);
		}
		
		if(outputBrowseField.getText().equals("Browse") && evt.getSource()==outputBrowseField){
			outputBrowseField.setText("");
			outputBrowseField.setForeground(Color.BLACK);
		}
		
		if(nameField.getText().equals("FileName") && evt.getSource()==nameField){
			nameField.setText("");
			nameField.setForeground(Color.BLACK);
		}
	}

	@Override
	public void focusLost(FocusEvent evt){
		if(browseField.getText().equals("") && evt.getSource()==browseField){
			browseField.setForeground(Color.GRAY);
			browseField.setText("Browse");
		}
		
		if(outputBrowseField.getText().equals("") && evt.getSource()==outputBrowseField){
			outputBrowseField.setText("Browse");
			outputBrowseField.setForeground(Color.GRAY);
		}
		
		if(nameField.getText().equals("") && evt.getSource()==nameField){
			nameField.setForeground(Color.GRAY);
			nameField.setText("FileName");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==browseButton){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.addChoosableFileFilter(new ImageFilter());
            fileChooser.setAcceptAllFileFilterUsed(false);
			
			int response = fileChooser.showOpenDialog(null);
			
			if(response==JFileChooser.APPROVE_OPTION) {
				browseField.setForeground(Color.BLACK);
				browseField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		}
		
		if(e.getSource()==outputBrowseButton){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(false);
			
			int response = fileChooser.showOpenDialog(null);
			
			if(response==JFileChooser.APPROVE_OPTION) {
				outputBrowseField.setForeground(Color.BLACK);
				outputBrowseField.setText(fileChooser.getSelectedFile().getAbsolutePath());
				File outputFolder = new File(outputBrowseField.getText());
			}
		}
		
		if(e.getSource()==approveButton){
			if(emptyBrowseField){
				JOptionPane.showMessageDialog(frame, "Enter the path!!", "Empty Fields!!", JOptionPane.WARNING_MESSAGE);
			} else if(wrongPath){
				JOptionPane.showMessageDialog(frame, "File doesn't exist at the entered path!!", "Incorrect Path", JOptionPane.ERROR_MESSAGE); 
			}
			
			if(emptyOutputField){
				JOptionPane.showMessageDialog(frame, "Enter the path!!", "Empty Fields!!", JOptionPane.WARNING_MESSAGE);
			} else if(wrongOutputPath){
				JOptionPane.showMessageDialog(frame, "Folder doesn't exist at the entered path!!", "Incorrect Path", JOptionPane.ERROR_MESSAGE); 
			}
			
			if(emptyNameField){
				JOptionPane.showMessageDialog(frame, "Enter the name!!", "Empty Fields!!", JOptionPane.WARNING_MESSAGE);
			}
			
			if(inputFile.exists() && outputFolder.isDirectory()){
				Img2Ascii asciiConvertor = new Img2Ascii(outputBrowseField.getText() + "\\" + nameField.getText());
				asciiConvertor.convertToAscii(browseField.getText());
				JOptionPane.showOptionDialog(
				frame,
				"Image succesfully converted!!", 
				"Completed!!", 
				JOptionPane.DEFAULT_OPTION, 
				0, 
				completedIcon, 
				null, 
				null);
			}
		}
	}
}