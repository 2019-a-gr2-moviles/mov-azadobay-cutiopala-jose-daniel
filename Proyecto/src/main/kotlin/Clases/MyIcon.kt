package Clases
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;


class MyIcon : Icon {

    override fun paintIcon(c: Component, g: Graphics, x: Int, y: Int) {
        val image = ImageIcon(javaClass.getResource("src/main/resources/biblio.png")).image
        g.drawImage(image, x, y, c)
    }

    override fun getIconWidth(): Int {
        return 50
    }

    override fun getIconHeight(): Int {
        return 50
    }

}