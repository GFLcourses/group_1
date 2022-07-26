package executor.model;

import java.util.List;
import java.util.Objects;

public class Scenario implements Comparable<Scenario> {
    private boolean nullObject;
    private String name;
    private String site;
    private List<Step> steps;

    public Scenario() {
        this.nullObject = true;
    }

    public Scenario(String name, String site, List<Step> steps) {
        this.name = name;
        this.site = site;
        this.steps = steps;
        this.nullObject = false;
    }

    public boolean isNull() {
        return this.nullObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.nullObject = false;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
        this.nullObject = false;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
        this.nullObject = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scenario that = (Scenario) o;
        return Objects.equals(name, that.name) && Objects.equals(site, that.site) && Objects.equals(steps, that.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, site, steps);
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "name='" + name + '\'' +
                ", site='" + site + '\'' +
                ", steps=" + steps +
                '}';
    }

    @Override
    public int compareTo(Scenario o) {
        if (this.name.equals(o.name)){
            return 1;
        } else {
            return -1;
        }
    }
}
