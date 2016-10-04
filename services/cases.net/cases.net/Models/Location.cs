using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Org.Naima.Services.Cases.Models
{
    public class Location
    {
        public int Id { get; set; }
        public String Locality { get; set; }
        public String SubDistrict { get; set; }
        public String District { get; set; }

    }
}