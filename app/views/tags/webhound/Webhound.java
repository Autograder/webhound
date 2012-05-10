package views.tags.webhound;

import com.handinteractive.mobile.UAgentInfo;

import groovy.lang.Closure;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import play.exceptions.TagInternalException;
import play.exceptions.TemplateExecutionException;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Http;
import play.mvc.Scope.Flash;
import play.mvc.Scope.Session;
import play.templates.JavaExtensions;
import play.templates.FastTags;
import play.templates.GroovyTemplate.ExecutableTemplate;
import play.utils.HTML;

@FastTags.Namespace("webhound")
public class Webhound extends FastTags {

    public static void _isMobile(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) {
        if(args.containsKey("arg")) {
            HashMap<String, ?> headers = (HashMap<String, ?>) args.get("arg");
            String userAgent = headers.get("user-agent").toString();
            String accept = headers.get("accept").toString();

            UAgentInfo ua = new UAgentInfo(userAgent, accept);

            if(ua.detectMobileLong()) {
                out.println(JavaExtensions.toString(body));
            }
        }
    }

    public static void _isNotMobile(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) {
        if(args.containsKey("arg")) {
            HashMap<String, ?> headers = (HashMap<String, ?>) args.get("arg");
            String userAgent = headers.get("user-agent").toString();
            String accept = headers.get("accept").toString();

            UAgentInfo ua = new UAgentInfo(userAgent, accept);

            if(!ua.detectMobileLong()) {
                out.println(JavaExtensions.toString(body));
            }
        }
    }
}