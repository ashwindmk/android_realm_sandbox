package com.ashwin.realmsandbox.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class SkillModel extends RealmObject {
    @PrimaryKey
    @Required
    public String name;

    public int level;

    public SkillModel() { }

    public static SkillModel from(Skill skill) {
        SkillModel model = new SkillModel();
        model.name = skill.getName();
        model.level = skill.getLevel();
        return model;
    }

    public Skill toObject() {
        return new Skill(name, level);
    }
}
