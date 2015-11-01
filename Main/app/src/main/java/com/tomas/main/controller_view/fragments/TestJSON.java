package com.tomas.main.controller_view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tomas.main.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tomas on 01/11/2015.
 */
public class TestJSON extends Fragment
{
    /*
            Tabs

     */
    private static final String ARGS_SELECTION_NUMBER = "selection_number";

    public static Fragment newInstace(int selectNumber)
    {
        TestJSON testJSON = new TestJSON();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_SELECTION_NUMBER, selectNumber);
        testJSON.setArguments(bundle);
        return testJSON;
    }
    /*
        end tabs
     */
    private final String JSON = "{\"name\":\"tomas\",\"last_name\":\"galicia\"}";
    private final String JSONARRAY = "{\"nameArray\":[{\"name\":\"tomas\",\"last_name\":\"galicia\"},{\"name\":\"pedrita\",\"last_name\":\"solovina\"}]}";
    private TextView codeJSON, codeJSONArray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle)
    {
        View rootView = inflater.inflate(R.layout.fragment_json, viewGroup, false);
        this.codeJSON = (TextView) rootView.findViewById(R.id.codeJSONXML);
        this.codeJSONArray = (TextView) rootView.findViewById(R.id.codeJSOArrayNXML);
        this.codeJSON.setText(this.getTextJson(), TextView.BufferType.NORMAL);
        this.codeJSONArray.setText(this.getTextJsonArray(), TextView.BufferType.NORMAL);
        return rootView;
    }
    private String getTextJson()
    {
        String name, lest_name, code = "";
        try
        {
            JSONObject jsonObject = new JSONObject(this.JSON);
            name = jsonObject.getString("name");
            lest_name = jsonObject.getString("last_name");
            code = "valores: "+ name +" "+ lest_name +"Codigo\nprivate String getTextJson()\n" +
                    "    {\n" +
                    "        String name, lest_name, code = \"\";\n" +
                    "        try\n" +
                    "        {\n" +
                    "            JSONObject jsonObject = new JSONObject(JSON);\n" +
                    "            name = jsonObject.getString(\"name\");\n" +
                    "            lest_name = jsonObject.getString(\"last_name\");\n" +
                    "            code = \"valores: \"+ name + lest_name +\"Codigo\\n\";\n" +
                    "        }catch (JSONException e)\n" +
                    "        {\n" +
                    "            e.printStackTrace();\n" +
                    "        }\n" +
                    "        finally \n" +
                    "        {\n" +
                    "            return code;\n" +
                    "        }\n" +
                    "    }";
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return code;
        }
    }
    private String getTextJsonArray()
    {
        String message = "valores: ";
        try
        {
            JSONObject jsonObject = new JSONObject(this.JSONARRAY);
            JSONArray jsonArray = jsonObject.getJSONArray("nameArray");
            for(int cont = 0; cont < jsonArray.length(); cont++)
            {
                JSONObject jsonObject1 = jsonArray.getJSONObject(cont);
                message += jsonObject1.getString("name") + " " + jsonObject1.getString("last_name") + "\n";
            }
            message += "private String getTextJsonArray()\n" +
                    "    {\n" +
                    "        String message = \"valores: \", code = \"\";\n" +
                    "        try\n" +
                    "        {\n" +
                    "            JSONObject jsonObject = new JSONObject(this.JSONARRAY);\n" +
                    "            JSONArray jsonArray = jsonObject.getJSONArray(\"nameArray\");\n" +
                    "            for(int cont = 0; cont < jsonArray.length(); cont++)\n" +
                    "            {\n" +
                    "                JSONObject jsonObject1 = jsonArray.getJSONObject(cont);\n" +
                    "                message += jsonObject1.getString(\"name\") + \" \" + jsonObject1.getString(\"last_name\") + \"\\n\";\n" +
                    "            }\n" +
                    "            message += \"\";\n" +
                    "        }catch (JSONException e)\n" +
                    "        {\n" +
                    "            e.printStackTrace();\n" +
                    "        }finally\n" +
                    "        {\n" +
                    "            return message;            \n" +
                    "        }\n" +
                    "    }";
        }catch (JSONException e)
        {
            e.printStackTrace();
        }finally
        {
            return message;
        }
    }

}
