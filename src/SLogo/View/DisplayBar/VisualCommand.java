package SLogo.View.DisplayBar;

import SLogo.View.SLogoGUIElement;

/**
 * Extends to commands in ItemLists
 *
 * @author Alex
 */
public interface VisualCommand extends SLogoGUIElement {
    /**
     * @return Command associated with node
     */
    String getCommand();
}
