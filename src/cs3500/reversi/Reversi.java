package cs3500.reversi;

import javax.swing.*;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiFrame;
import cs3500.reversi.view.ReversiGraphicalView;

public final class Reversi {
  public static void main(String[] args) {
    ReversiModel model = new BasicReversi();
    ReversiGraphicalView view = new ReversiFrame(model);
    view.setVisible(true);
  }
}
