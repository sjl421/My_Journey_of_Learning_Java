/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package thymeleafexamples.gtvg.web.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import thymeleafexamples.gtvg.business.entities.Order;
import thymeleafexamples.gtvg.business.services.CustomerService;
import thymeleafexamples.gtvg.business.services.OrderService;
import thymeleafexamples.gtvg.web.filter.GTVGFilter;

@Controller
public class OrderDetailsController{

	@RequestMapping("/order/details")
    public void details( final HttpServletRequest request, final HttpServletResponse response, Model model) throws IOException {
		
	  ITemplateEngine templateEngine = GTVGFilter.application.getTemplateEngine();
      final Integer orderId = Integer.valueOf(request.getParameter("orderId"));
      
      final OrderService orderService = new OrderService();
      final Order order = orderService.findById(orderId);
      
      final WebContext ctx = new WebContext(request, response, GTVGFilter.servletContext, request.getLocale());
      ctx.setVariable("order", order);
      
      templateEngine.process("order/details", ctx, response.getWriter());
    }
	
    public OrderDetailsController() {
        super();
    }
    
    
//    public void process(
//            final HttpServletRequest request, final HttpServletResponse response,
//            final ServletContext servletContext, final ITemplateEngine templateEngine)
//            throws Exception {
//        
//        final Integer orderId = Integer.valueOf(request.getParameter("orderId"));
//        
//        final OrderService orderService = new OrderService();
//        final Order order = orderService.findById(orderId);
//        
//        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
//        ctx.setVariable("order", order);
//        
//        templateEngine.process("order/details", ctx, response.getWriter());
//        
//    }

}
