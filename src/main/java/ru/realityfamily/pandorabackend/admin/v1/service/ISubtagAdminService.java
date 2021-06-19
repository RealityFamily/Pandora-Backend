package ru.realityfamily.pandorabackend.admin.v1.service;

import com.sun.istack.NotNull;
import ru.realityfamily.pandorabackend.shared.models.Subtag;

public interface ISubtagAdminService {


    Subtag addSubtagToSubcategoryByID(@NotNull String subcategoryId,@NotNull Subtag subtag);

    Subtag updateSubtagById(@NotNull Subtag subtag,@NotNull String subtagId);

    void deleteSubtag(@NotNull String subtagId);
}
