package site.nomoreparties.stellarburgers.api;

import java.util.List;

public class IngredientsGetAllResponse {
    private Boolean success;
    private List<Ingredient> data;

    public IngredientsGetAllResponse(Boolean success, List<Ingredient> data) {
        this.success = success;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Ingredient> getData() {
        return data;
    }

    public void setData(List<Ingredient> data) {
        this.data = data;
    }
}
