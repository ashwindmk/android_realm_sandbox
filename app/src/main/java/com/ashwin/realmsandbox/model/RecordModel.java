package com.ashwin.realmsandbox.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class RecordModel extends RealmObject {
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_EMAIL = "email";
    public static final String PROPERTY_DATE = "date";

    @PrimaryKey
    @Required
    public Long id;

    @Required
    public String name;

    public String email;

    public Date date;

    public RealmList<SkillModel> skills;

    public RecordModel() { }

    public RecordModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static RecordModel from(Record record) {
        RecordModel model = new RecordModel(record.getId(), record.getName());
        model.email = record.getEmail();
        model.date = record.getDate();
        RealmList<SkillModel> models = null;
        if (record.getSkills() != null) {
            models = new RealmList<>();
            for (Skill skill : record.getSkills()) {
                models.add(SkillModel.from(skill));
            }
        }
        model.skills = models;
        return model;
    }

    public Record toObject() {
        Record obj = new Record(id, name);
        obj.setEmail(email);
        obj.setDate(date);
        List<Skill> list = new ArrayList<>(skills.size());
        for (SkillModel model : skills) {
            list.add(model.toObject());
        }
        obj.setSkills(list);
        return obj;
    }
}
