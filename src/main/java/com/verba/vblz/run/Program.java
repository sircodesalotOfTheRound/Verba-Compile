package com.verba.vblz.run;

import com.verba.tools.display.ConsoleOutput;
import com.verba.vblz.run.argparse.ArgumentParser;
import com.verba.vblz.showusage.PrintUsageTask;

import java.io.Serializable;

/**
 * Created by sircodesalot on 14-7-8.
 */
public class Program implements Serializable {

    public static void main(String[] args) throws Exception {
        ArgumentParser parser = new ArgumentParser(args);
        parser.runApplication();
    }

}
