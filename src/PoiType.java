public class PoiType {
    public String poiType;

    public PoiType(String poiType) {
        this.poiType = poiType;
    }

    public String getPoiType() {
        return poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }

    @Override
    public String toString() {
        return "|| Poi Type: " + poiType;
    }
}
