/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//https://stackoverflow.com/questions/7830951/how-can-i-load-computer-directory-images-in-javafx

package Photoshop;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
/**
 *
 * @author samue
 */
public class MainController {

    private MainApp mainApp;
    private File file;  //Create file for the source

    public MainController(){
    }

    void setMainApp(MainApp aThis) {
        this.mainApp=mainApp;
    }
        
    @FXML
     ImageView foto;
    
    @FXML
    private void initialize(){ 
    }
    
    @FXML
    private void invert_button() throws IOException
    {
        //Read the file to a BufferedImage
        BufferedImage image = ImageIO.read(file);
        //Create a file for the output
        File output = new File("src\\Photoshop\\image.bmp");
        
        //INVERSIONE COLORI
        image = invert(image);
        //Write the image to the destination as a BMP
        ImageIO.write(image, "bmp", output);
    }
    
    @FXML
    private void blackwhite_button() throws IOException
    {
        BufferedImage image = ImageIO.read(file);
        //Create a file for the output
        File output = new File("src\\Photoshop\\image.bmp");
        
        image = blackwhite(image);
        //Write the image to the destination as a BMP
        ImageIO.write(image, "bmp", output);
    }
    
    
    @FXML
    private void print_foto_mother() throws IOException
    {
        Image image = new Image(file.toURI().toString());
        foto.setImage(image);
    }
    @FXML
    private void print_foto() throws IOException
    {
        File output = new File("src\\Photoshop\\image.bmp");
        Image image = new Image(output.toURI().toString());
        foto.setImage(image);
    }
    
    @FXML
    private void choose_file()
    {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("src\\Photoshop"));
        File selectedfile = fc.showOpenDialog(null);  //apre una finestra per selezionare il file da modificare
        this.file = selectedfile;  //dare il file selezionato ad una variabile globale, così da poterlo usare localmente
    }
    
    //RESTITUISCE UN'IMMAGINE CON I COLORI INVERTITI
    public BufferedImage invert(BufferedImage image)
    {
        //assegna ad ogni pixel l'opposto del suo colore
        //https://dyclassroom.com/image-processing-project/how-to-convert-a-color-image-into-negative
        
        for(int y = 0; y < image.getHeight(); y++)
        {
            for(int x = 0; x < image.getWidth(); x++)
            {
              int p = image.getRGB(x,y);
              int a = (p>>24)&0xff;
              int r = (p>>16)&0xff;
              int g = (p>>8)&0xff;
              int b = p&0xff;
              //subtract RGB from 255
              r = 255 - r;
              g = 255 - g;
              b = 255 - b;
              //set new RGB value
              p = (a<<24) | (r<<16) | (g<<8) | b;
              image.setRGB(x, y, p);
            }
        }
        return image;
    }
    
    //RESTITUISCE UN'IMMAGINE IN BIANCO E NERO
    public BufferedImage blackwhite(BufferedImage image)
    {
        //https://stackoverflow.com/questions/14513542/how-to-convert-image-to-black-and-white-using-java

        BufferedImage blackAndWhiteImg = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D graphics = blackAndWhiteImg.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        
        return blackAndWhiteImg;
    }
}
