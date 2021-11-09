package ua.goit.module4.queries;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.models.DbModel;
import ua.goit.module4.models.ModelsList;
import ua.goit.module4.models.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillQuery extends AbstractQuery {

    private static SkillQuery instance;

    private SkillQuery(DbConnector dbConnector) {
        super(dbConnector);
    }

    public static SkillQuery getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new SkillQuery(dbConnector);
        }
        return instance;
    }

    @Override
    protected String getTableName() {
        return "skills";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return Skill.class;
    }

    @Override
    protected ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {
        ModelsList list = new ModelsList();

        while (resultSet.next()) {
            Skill skill = new Skill();
            skill.setId(resultSet.getInt("id"));
            skill.setLanguage(resultSet.getString("language"));
            skill.setLevel(resultSet.getString("level"));
            list.add(skill);
        }
        resultSet.close();
        return list;
    }

}
