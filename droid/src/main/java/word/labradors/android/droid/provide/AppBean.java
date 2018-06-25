package word.labradors.android.droid.provide;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * app更新基类
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppBean {
    public String id;
    public String name;
    public String bundleId;
    public String icon;
    public String type;
    public String userId;
    public Version version;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }
}
