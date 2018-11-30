package pl.michalak.adam.output;

import pl.michalak.adam.components.ComponentsAPI;
import pl.michalak.adam.components.Symbol;

class BoardPrinter {
    ComponentsAPI componentsAPI;

    BoardPrinter(ComponentsAPI componentsAPI){
        this.componentsAPI = componentsAPI;
    }

    String boardToString() {
        StringBuilder out = new StringBuilder();
        out.append("\n\n");
        for (int slot = 0; slot < componentsAPI.getNumberOfSlotsOnBoard(); slot++) {
            //go to new row
            if (slot > 0 && slot % componentsAPI.getBoardSideSize() == 0) {
                out.append("\n");
                for (int width = 0; width < componentsAPI.getBoardSideSize(); width++) {
                    if(componentsAPI.getNumberOfSlotsOnBoard() > 9)
                        out.append("-----");
                    else
                        out.append("----");
                }
                out.append("\n");
            }
            if (componentsAPI.getSymbolFromSlot(slot).equals(Symbol.EMPTY)) {
                if(componentsAPI.getNumberOfSlotsOnBoard() > 9 && slot < 9)
                    out.append("  " + (slot+1) + " ");
                else
                    out.append(" " + (slot+1) + " ");
            }
            else {
                out.append("  ").append(componentsAPI.getSymbolFromSlot(slot)).append(" ");
            }
            if(slot % componentsAPI.getBoardSideSize() != componentsAPI.getBoardSideSize()-1)
                out.append("|");
        }
        out.append("\n\n");
        return out.toString();
    }
}
