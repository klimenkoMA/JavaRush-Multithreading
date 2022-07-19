package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
	private View view;
	private HTMLDocument document;
	private File currentFile;

	public Controller(View view) {
		this.view = view;
	}

	public HTMLDocument getDocument() {
		return document;
	}

	public void createNewDocument(){
		view.selectHtmlTab();
		resetDocument();

		view.setTitle("HTML редактор");
		currentFile = null;

	}

	public void openDocument(){
		try{

			view.selectHtmlTab();

			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new HTMLFileFilter());

			if(chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION){
				currentFile = chooser.getSelectedFile();

				resetDocument();

				view.setTitle(currentFile.getName());

				FileReader reader = new FileReader(currentFile);
				HTMLEditorKit kit = new HTMLEditorKit();

				kit.read(reader, document, 0);
				reader.close();

				view.resetUndo();
			}

		}catch(Exception e){
			ExceptionHandler.log(e);
		}
	}

	public void saveDocument(){
		try{
			view.selectHtmlTab();

			if(currentFile !=null){

				FileWriter writer = new FileWriter(currentFile);
				HTMLEditorKit kit = new HTMLEditorKit();

				kit.write(writer, document, 0, document.getLength());
				writer.close();
			}else{
				saveDocumentAs();
			}
		}catch(Exception e){
			ExceptionHandler.log(e);
		}
	}

	public void saveDocumentAs(){
		try{
			view.selectHtmlTab();

			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new HTMLFileFilter());

			if((chooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION)){
				currentFile = chooser.getSelectedFile();
				view.setTitle(currentFile.getName());

				FileWriter writer = new FileWriter(currentFile);

				HTMLEditorKit kit = new HTMLEditorKit();
				kit.write(writer, document, 0 , document.getLength());

			}

		}catch(Exception e){
			ExceptionHandler.log(e);
		}
	}



	public String getPlainText(){

		try{
			StringWriter writer = new StringWriter();
			HTMLEditorKit kit = new HTMLEditorKit();
			kit.write(writer, document, 0, document.getLength());
			return writer.toString();
		}catch(Exception e){
			ExceptionHandler.log(e);
			return "";
		}
	}

	public void setPlainText(String text){
		try {
			resetDocument();
			StringReader reader = new StringReader(text);
			HTMLEditorKit kit = new HTMLEditorKit();
			kit.read(reader, document, 0);
		}catch(Exception e){
			ExceptionHandler.log(e);
		}
	}
	
	public void resetDocument(){

		if(document != null) {
			document.removeUndoableEditListener(view.getUndoListener());
		}

		HTMLEditorKit kit = new HTMLEditorKit();
		document = (HTMLDocument) kit.createDefaultDocument();
		document.addUndoableEditListener(view.getUndoListener());

		view.update();
	}

	public static void main(String[] args) {
		View view = new View();
		Controller controller = new Controller(view);
		view.setController(controller);

		view.init();
		controller.init();

	}

	public void init() {
		createNewDocument();
	}

	public void exit() {
		System.exit(0);
	}
}
