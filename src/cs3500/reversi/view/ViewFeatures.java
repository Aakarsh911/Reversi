package cs3500.reversi.view;

import java.util.function.Consumer;

public interface ViewFeatures {
  void move(int row, int col);
  void pass();

}
