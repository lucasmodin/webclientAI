package webclient.webclientai.raiderio_dto;

public class AffixDTO {
    private int id;
    private String name;
    private String description;
    private String icon;
    private String icon_url;
    private String wowhead_url;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getIcon_url() { return icon_url; }
    public void setIcon_url(String icon_url) { this.icon_url = icon_url; }

    public String getWowhead_url() { return wowhead_url; }
    public void setWowhead_url(String wowhead_url) { this.wowhead_url = wowhead_url; }
}
