package Observer;

/**
 * -----------------------------------------------------------------------------------
 * @Authors     : Slimani Walid & Steiner Jeremiah
 * @Description : Cette interface définit la méthode update, qui est utilisée pour mettre à jour
 *                les observateurs lorsqu'un changement d'état se produit dans l'objet observé.
 * @Info        : /
 * -----------------------------------------------------------------------------------
 **/

public interface IObservable {
    // region Method
    /**
     * Nom         : update
     * Description : Met à jour les observateurs lorsque des changements d'état se produisent
     *               dans l'objet observé.
     */
    void update();
    // endregion
}
