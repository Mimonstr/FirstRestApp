package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View
{

    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace(".", "/") + "/vacancies.html";
    @Override
    public void update(List<Vacancy> vacancies)
    {
        try
        {
            String newContent = getUpdatedFileContent(vacancies);
            updateFile(newContent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod()
    {
        controller.onCitySelect("java");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies)
    {
        try
        {
            Document document = getDocument();
            Elements templateHidden = document.getElementsByClass("template");
            Element template = templateHidden.clone().removeAttr("style").removeClass("template").get(0);

            //remove all prev vacancies
            Elements prevVacancies = document.getElementsByClass("vacancy");

            for (Element redundant : prevVacancies)
            {
                if (!redundant.hasClass("template"))
                {
                    redundant.remove();
                }
            }

            //add new vacancies
            for (Vacancy vacancy : vacancies)
            {
                Element vacancyElement = template.clone();

                Element vacancyLink = vacancyElement.getElementsByAttribute("href").get(0);
                vacancyLink.appendText(vacancy.getTitle());
                vacancyLink.attr("href", vacancy.getUrl());
                Element city = vacancyElement.getElementsByClass("city").get(0);
                city.appendText(vacancy.getCity());
                Element companyName = vacancyElement.getElementsByClass("companyName").get(0);
                companyName.appendText(vacancy.getCompanyName());
                Elements salaryEls = vacancyElement.getElementsByClass("salary");
                Element salary = salaryEls.get(0);
                salary.appendText(vacancy.getSalary());

                templateHidden.before(vacancyElement.outerHtml());
            }
            return document.html();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            
        }
        return "Some exception occurred";
    }

    protected Document getDocument() throws IOException
    {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
    private void updateFile(String content)
    {
        try(FileWriter fileWriter = new FileWriter(filePath))
        {
            fileWriter.write(content);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
