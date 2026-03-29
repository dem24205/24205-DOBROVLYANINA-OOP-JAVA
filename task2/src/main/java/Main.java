import factory.Factory;
import gui.FactoryGUI;
import utilities.Config;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String configPath = args.length > 0 ? args[0] : "default_config.txt";
        Config config = new Config(configPath);
        Factory factory = new Factory(config);
        factory.start();
        SwingUtilities.invokeLater(() -> {
            FactoryGUI factoryView = new FactoryGUI(factory);
            factoryView.setVisible(true);
        });
    }
}
