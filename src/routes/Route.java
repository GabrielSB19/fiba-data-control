package routes;

public enum Route {
    SPLASH("/ui/assets/screens/loader.fxml"),MAINPANE("/ui/assets/screens/mainPane.fxml"),LIGHT("/ui/assets/styles/light.css"),DARK("/ui/assets/styles/dark.css"),PLAYER("/ui/assets/screens/player.fxml");

    private String route;

    private Route(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}