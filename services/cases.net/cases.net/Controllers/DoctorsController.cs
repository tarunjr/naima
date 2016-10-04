using Org.Naima.Services.Cases.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.OData;

namespace cases.net.Controllers
{
    public class DoctorsController : ODataController
    {
        CasesContext db = new CasesContext();

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }
        [EnableQuery]
        public IQueryable<Doctor> Get()
        {
            return db.Doctors;
        }
        [EnableQuery]
        public SingleResult<Location> GetLocation([FromODataUri]int key)
        {
            var result = db.Doctors.Where(l => l.Id == key).Select(d => d.Location);
            return SingleResult.Create<Location>(result);
        }
   
        [EnableQuery]
        public IQueryable<Case> GetCases([FromODataUri] int key)
        {
            return db.Doctors.Where(m => m.Id.Equals(key)).SelectMany(m => m.Cases);
        }
        public async Task<IHttpActionResult> Post(Doctor doctor)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            db.Doctors.Add(doctor);
            await db.SaveChangesAsync();
            return Created(doctor);
        }
    }
}
